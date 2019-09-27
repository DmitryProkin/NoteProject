package com.dmitry.noteProject.Repositories;

import com.dmitry.noteProject.Entities.NoteEntity;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<NoteEntity,Integer> {
}
