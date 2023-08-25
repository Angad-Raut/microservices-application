package com.projectx.basepackage.service;

import com.projectx.basepackage.exceptions.InvalidDataException;
import com.projectx.basepackage.exceptions.ResourceNotFoundException;
import com.projectx.basepackage.payloads.EntityIdDto;
import com.projectx.basepackage.payloads.LibraryDto;
import com.projectx.basepackage.payloads.LibraryListDto;

import java.util.List;

public interface LibraryService {
    String insertOrUpdate(LibraryDto dto)throws InvalidDataException;
    LibraryDto getById(EntityIdDto dto)throws ResourceNotFoundException;
    String deleteById(EntityIdDto dto)throws ResourceNotFoundException;
    List<LibraryListDto> getAllLibraryList();
}
