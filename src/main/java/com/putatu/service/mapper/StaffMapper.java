package com.putatu.service.mapper;

import com.putatu.domain.*;
import com.putatu.service.dto.StaffDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Staff and its DTO StaffDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, PositionMapper.class})
public interface StaffMapper extends EntityMapper<StaffDTO, Staff> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "position.id", target = "positionId")
    StaffDTO toDto(Staff staff); 

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "positionId", target = "position")
    Staff toEntity(StaffDTO staffDTO);

    default Staff fromId(Long id) {
        if (id == null) {
            return null;
        }
        Staff staff = new Staff();
        staff.setId(id);
        return staff;
    }
}
