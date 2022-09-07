package me.iankit.simplechat.chat

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@RequestMapping("groups")
@RestController
class GroupController(private val groupService: GroupService) {
    @PostMapping
    fun createGroup(@RequestBody dto: CreateGroupDto): Mono<Group> {
        return groupService.createGroup(dto)
    }

    @GetMapping("/{groupId}")
    fun getGroupById(@PathVariable groupId: String): Mono<Group> {
        return groupService.getGroupById(groupId)
    }

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun allGroups(): Flux<Group> {
        return groupService.allGroups()
            .log()
            .subscribeOn(Schedulers.boundedElastic())
    }
}