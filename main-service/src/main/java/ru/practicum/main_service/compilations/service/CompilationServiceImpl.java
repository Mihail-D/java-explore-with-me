package ru.practicum.main_service.compilations.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.main_service.categories.dto.CategoriesMapper;
import ru.practicum.main_service.compilations.dto.CompilationDto;
import ru.practicum.main_service.compilations.dto.CompilationMapper;
import ru.practicum.main_service.compilations.dto.NewCompilationDto;
import ru.practicum.main_service.compilations.dto.UpdateCompilationRequest;
import ru.practicum.main_service.compilations.model.Compilation;
import ru.practicum.main_service.compilations.repository.CompilationRepository;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.dto.mapper.EventMapper;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.repository.EventRepository;
import ru.practicum.main_service.exception.ObjectNotFoundException;
import ru.practicum.main_service.users.dto.UserMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationsRepository;
    private final EventRepository eventRepository;

    @Override
    public List<CompilationDto> getAllCompilations(Boolean pinned, Integer from, Integer size) {
        int offset = from > 0 ? from / size : 0;
        PageRequest page = PageRequest.of(offset, size);
        List<Compilation> compilations;
        if (pinned == null) {
            compilations = compilationsRepository.findAll(page).getContent();
        } else {
            compilations = compilationsRepository.findAllByPinned(pinned, page);
        }
        if (compilations.isEmpty()) {
            return Collections.emptyList();
        }
        List<CompilationDto> collect = compilations.stream().map(compilation ->
                CompilationMapper.toCompilationDto(
                        compilation,
                        mapToDto(compilation.getEvents())
                )).collect(Collectors.toList());
        log.info("GET request to receive collections of events");

        return collect;
    }

    @Override
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {
        List<Event> events = new ArrayList<>();
        if (newCompilationDto.getEvents() != null) {
            events = eventRepository.findAllById(newCompilationDto.getEvents());
        }
        Compilation compilation = CompilationMapper.toCompilation(newCompilationDto, events);
        Compilation result = compilationsRepository.save(compilation);
        log.info("POST request to add a new selection with id: {}", compilation.getId());

        return CompilationMapper.toCompilationDto(result, mapToDto(events));
    }

    @Override
    public void deleteCompilation(Long compId) {
        getCompilation(compId);
        log.info("DELETE request to delete a selection with id: {}", compId);
        compilationsRepository.deleteById(compId);
    }

    @Override
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest request) {
        Compilation compilation = getCompilation(compId);
        if (request.getEvents() != null) {
            compilation.setEvents(getFromId(request.getEvents()));
        }
        if (request.getPinned() != null) {
            compilation.setPinned(request.getPinned());
        }
        if (request.getTitle() != null) {
            compilation.setTitle(request.getTitle());
        }
        Compilation result = compilationsRepository.save(compilation);

        List<Event> events = new ArrayList<>();
        if (request.getEvents() != null) {
            events = eventRepository.findAllById(request.getEvents());
        }
        log.info("PATH request to change the selection of events by id: {}", compId);

        return CompilationMapper.toCompilationDto(result, mapToDto(events));
    }

    @Override
    public CompilationDto getCompilationsById(Long compId) {
        Compilation compilation = getCompilation(compId);
        log.info("GET request to receive a selection of events by id: {}", compId);
        return CompilationMapper.toCompilationDto(compilation, mapToDto(compilation.getEvents()));
    }

    private List<Event> getFromId(List<Long> evenIdList) {
        List<Event> events = eventRepository.findAllByIdIn(evenIdList);
        if (events.size() != evenIdList.size()) {
            List<Long> list = new ArrayList<>();
            for (Event event : events) {
                Long id = event.getId();
                list.add(id);
            }

            evenIdList.removeAll(list);
        }
        return events;
    }

    private List<EventShortDto> mapToDto(List<Event> events) {
        return events.stream().map(event ->
                EventMapper.toEventShortDto(
                        event,
                        CategoriesMapper.toCategoryDto(event.getCategory()),
                        UserMapper.toUserDto(event.getInitiator())
                )).collect(Collectors.toList());
    }

    private Compilation getCompilation(Long compId) {
        return compilationsRepository.findById(compId)
                .orElseThrow(() -> new ObjectNotFoundException("This collection does not exist"));
    }
}