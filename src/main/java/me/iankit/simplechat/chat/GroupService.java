package me.iankit.simplechat.chat;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GroupService {
    private final GroupDao groupDao;

    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Mono<Group> createGroup(CreateGroupDto dto) {
        return groupDao.save(dto.toEntity());
    }

    public Flux<Group> getAllGroups() {
        return groupDao.findAll();
    }
}
