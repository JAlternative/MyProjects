package main;

import main.responce.Work;
import main.responce.WorkRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkService{
    private final WorkRepository workRepository;


    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public List<Work> list() {
        Iterable<Work> workIterable = workRepository.findAll();
        ArrayList<Work> works = new ArrayList<>();
        for (Work work : workIterable) {
            works.add(work);
        }
        return works;
    }

    public int add(Work work) {
        Work newWork = workRepository.save(work);
        return newWork.getId();
    }
    public void deleteAll(){
        workRepository.deleteAll();
    }


    public ResponseEntity delete(int id) {
        Optional<Work> optionalWork = workRepository.findById(id);
        if (!optionalWork.isPresent()) {
           throw new EntityNotFoundException("Ошибка 404! При удалении ID дела не найдено!");

        }
        workRepository.deleteById(id);
        return new ResponseEntity(optionalWork.get(), HttpStatus.OK);
    }

    public ResponseEntity get(int id) {
        Optional<Work> optionalWork = workRepository.findById(id);
        if (!optionalWork.isPresent()) {
            throw new EntityNotFoundException("Ошибка 404! При поиске ID дела не найдено!");
        }
        return new ResponseEntity(optionalWork.get(), HttpStatus.OK);
    }


    public int putWork(Work work) {
        int id = work.getId();
        String name = work.getName();
        int year = work.getYear();
        delete(id);

        Work work1 = new Work();
        work1.setId(id);
        work1.setName(name);
        work1.setYear(year);

        add(work1);
        return work1.getId();
    }


}
