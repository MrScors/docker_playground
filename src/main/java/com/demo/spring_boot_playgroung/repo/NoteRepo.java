package com.demo.spring_boot_playgroung.repo;

import com.demo.spring_boot_playgroung.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends CrudRepository<Note, Long> {

}
