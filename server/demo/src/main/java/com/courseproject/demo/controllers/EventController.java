package com.courseproject.demo.controllers;

import com.courseproject.demo.dtos.EventDto;
import com.courseproject.demo.entities.Event;
import com.courseproject.demo.mappers.EventMapper;
import com.courseproject.demo.repositories.EventRepository;
import com.courseproject.demo.repositories.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер мероприятий.
 *
 * <p>
 * Автор: Korchanova
 * Версия: 1.0
 * </p>
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;

    /**
     * Конструктор контроллера мероприятий.
     *
     * @param eventRepository Репозиторий мероприятий.
     * @param eventMapper     Маппер мероприятий.
     * @param userRepository  Репозиторий пользователей.
     */
    public EventController(EventRepository eventRepository,
                           EventMapper eventMapper,
                           UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userRepository = userRepository;
    }

    /**
     * Получение списка всех мероприятий.
     *
     * @return Список DTO мероприятий.
     */
    @GetMapping("/all")
    public List<EventDto> getAllEvents() {

        return eventRepository.findAll().stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Поиск мероприятия по имени.
     *
     * @param name Имя мероприятия.
     * @return Список DTO найденных мероприятий.
     */
    @GetMapping("/find/{name}")
    public List<EventDto> find(@PathVariable String name) {
        return eventRepository.findByName(name).stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Удаление мероприятия по ID.
     *
     * @param id ID мероприятия.
     */
    @GetMapping("/remove/{id}")
    public void remove(@PathVariable int id) {
        eventRepository.deleteById(id);
    }

    /**
     * Создание нового мероприятия.
     *
     * @param eventDto DTO нового мероприятия.
     * @return DTO созданного мероприятия.
     */
    @PostMapping("/new")
    public EventDto createEvent(@RequestBody @NotNull @Valid EventDto eventDto) {
        return eventMapper.toDto(eventRepository.save(eventMapper.toEntity(eventDto)));
    }

    /**
     * Обновление информации о мероприятии.
     *
     * @param eventDto DTO с информацией о мероприятии.
     * @return Обновленное DTO мероприятия.
     */
    @PostMapping("/update")
    public EventDto updateEvent(@RequestBody @NotNull @Valid EventDto eventDto) {
        if (eventDto.getId() == null) {
            throw new IllegalArgumentException("Event id must not be null");
        }
        Event event = eventRepository.findById(eventDto.getId()).orElseThrow();
        Event updatedEvent = eventMapper.partialUpdate(eventDto, event);
        return eventMapper.toDto(eventRepository.save(updatedEvent));
    }
}
