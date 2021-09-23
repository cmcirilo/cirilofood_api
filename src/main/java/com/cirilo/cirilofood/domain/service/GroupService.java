package com.cirilo.cirilofood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cirilo.cirilofood.domain.exception.EntityInUseException;
import com.cirilo.cirilofood.domain.exception.GroupNotFoundException;
import com.cirilo.cirilofood.domain.model.Group;
import com.cirilo.cirilofood.domain.repository.GroupRepository;

@Service
public class GroupService {

    private static final String MSG_GROUP_IN_USE = "Group Id %d does not be removed, because is in use.";

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(Long groupId) {
        try {
            groupRepository.deleteById(groupId);
            groupRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(groupId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_GROUP_IN_USE, groupId));
        }
    }

    public Group find(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }

}
