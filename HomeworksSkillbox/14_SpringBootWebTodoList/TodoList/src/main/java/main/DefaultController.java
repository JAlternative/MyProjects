package main;

import main.responce.Work;
import main.responce.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController
{
    @Autowired
    WorkRepository workRepository;

    @RequestMapping("/")
    public String index(Model model)
    {
        Iterable<Work> workIterable = workRepository.findAll();
        ArrayList<Work> works = new ArrayList<>();
        for (Work work :workIterable){
            works.add(work);
        }
        model.addAttribute("works", works);
        model.addAttribute("worksCount", works.size());

        return "index";

    }
}
