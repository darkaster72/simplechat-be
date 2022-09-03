package me.iankit.simplechat.chat;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequestMapping("groups")
@RestController
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    Mono<Group> createGroup(@RequestBody CreateGroupDto dto) {
        return groupService.createGroup(dto);
    }

    @GetMapping("/{groupId}")
    Mono<Group> getGroupById(@PathVariable String groupId) {
        return groupService.getGroupById(groupId);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Group> getAllGroups() {
        return groupService.getAllGroups()
                .subscribeOn(Schedulers.boundedElastic());
    }
}