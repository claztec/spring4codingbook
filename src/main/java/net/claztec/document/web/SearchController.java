package net.claztec.document.web;

import net.claztec.document.data.DocumentDao;
import net.claztec.document.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by claztec on 15. 3. 19.
 */

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    DocumentDao documentDao;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String searchAll(Model model) {

        List<Document> list = documentDao.getAll();
        for(Document document : list) {
            System.out.println(document.getName());
        }
        model.addAttribute("docs", documentDao.getAll());
//        return "search/all2.html";
        return "search/all2.jspx";
    }
}
