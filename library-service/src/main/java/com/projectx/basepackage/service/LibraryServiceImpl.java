package com.projectx.basepackage.service;

import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.models.LibraryDetails;
import com.projectx.basepackage.payloads.EntityIdDto;
import com.projectx.basepackage.payloads.LibraryDto;
import com.projectx.basepackage.payloads.LibraryListDto;
import com.projectx.basepackage.repository.LibraryRepository;
import com.projectx.basepackage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Transactional
    @Override
    public String insertOrUpdate(LibraryDto dto) throws InvalidDataException {
        LibraryDetails libraryDetails = null;
        Boolean flag = false;
        if (dto.getId()==null) {
                libraryDetails = LibraryDetails.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .email(dto.getEmail())
                        .phone(dto.getPhone())
                        .build();
        } else {
                libraryDetails = libraryRepository.getById(dto.getId());
                if (!dto.getName().equals(libraryDetails.getName())) {
                    libraryDetails.setName(dto.getName());
                }
                if (!dto.getAddress().equals(libraryDetails.getAddress())) {
                    libraryDetails.setAddress(dto.getAddress());
                }
                if (!dto.getEmail().equals(libraryDetails.getEmail())) {
                    libraryDetails.setEmail(dto.getEmail());
                }
                if (!dto.getPhone().equals(libraryDetails.getPhone())) {
                    libraryDetails.setPhone(dto.getPhone());
                }
                libraryDetails.setUpdateTime(new Date());
                flag = true;
        }
        try {
             LibraryDetails details = libraryRepository.save(libraryDetails);
             if (details!=null && flag) {
                 return Constants.UPDATE_MSG;
             } else if (details!=null) {
                 return Constants.INSERT_MSG;
             } else {
                 throw new InvalidDataException(Constants.ERROR_MSG);
             }
        } catch (Exception e) {
            throw new InvalidDataException(Constants.ERROR_MSG);
        }
    }

    @Override
    public LibraryDto getById(EntityIdDto dto) throws ResourceNotFoundException {
        try {
            LibraryDetails details = libraryRepository.getById(dto.getEntityId());
            if (details!=null) {
                return LibraryDto.builder()
                        .id(details.getId())
                        .name(details.getName())
                        .address(details.getAddress())
                        .email(details.getEmail())
                        .phone(details.getPhone())
                        .build();
            } else {
                throw new ResourceNotFoundException(Constants.LIBRARY_DETAILS_NOT_FOUND_MSG);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(Constants.LIBRARY_DETAILS_NOT_FOUND_MSG);
        }
    }

    @Override
    public String deleteById(EntityIdDto dto) throws ResourceNotFoundException {
        try {
            LibraryDetails details = libraryRepository.getById(dto.getEntityId());
            if (details!=null) {
                libraryRepository.deleteById(details.getId());
                return Constants.DELETE_MSG;
            } else {
                throw new ResourceNotFoundException(Constants.LIBRARY_DETAILS_NOT_FOUND_MSG);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(Constants.LIBRARY_DETAILS_NOT_FOUND_MSG);
        }
    }

    @Override
    public List<LibraryListDto> getAllLibraryList() {
        try {
            List<LibraryDetails> libraryDetailsList = libraryRepository.findAll();
            AtomicInteger index = new AtomicInteger(0);
            if (libraryDetailsList!=null && !libraryDetailsList.isEmpty()) {
                return libraryDetailsList.stream()
                        .map(data -> LibraryListDto.builder()
                                .srNo(index.incrementAndGet())
                                .id(data.getId())
                                .name(data.getName())
                                .address(data.getAddress())
                                .email(data.getEmail())
                                .phone(data.getPhone())
                                .build())
                        .collect(Collectors.toList());
            } else {
                return new ArrayList<LibraryListDto>();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
