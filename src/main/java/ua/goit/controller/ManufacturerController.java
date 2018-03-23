package ua.goit.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.model.Manufacturer;
import ua.goit.service.ManufacturerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/manufacts")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

//    private static final Logger LOGGER = LogManager.getLogger(ManufacturerController.class);
//    private ManufacturerDAO manufacturerDAO = HibernateManufacturerDAOImpl.getInstance();
//
//    @Override
//
//    public void init() throws ServletException {
//    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllManufacturers() {
        List<Manufacturer> manufacturerList = this.manufacturerService.getAll();

        if (manufacturerList.isEmpty()) {
            return "error";
        }

        return "manufacts";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getManufacturer(@PathVariable("manufId") Long manufId) {
        if (manufId == null) {
            return "error";
        }

        Manufacturer manufacturer = this.manufacturerService.getById(manufId);

        if (manufacturer == null) {
            return "error";
        }

        return "manufacts";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            Long id = Long.parseLong(req.getParameter(ID_PARAM));
//            Manufacturer manufacturer = manufacturerDAO.getById(id);
//            req.setAttribute(MANUF_ATTR, manufacturer);
//            req.getRequestDispatcher(EDIT_MANUF_JSP).forward(req, resp);
//        } catch(Exception e) {
//            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
//        }
//    }
//
//    private void forwardToManufs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/manufs").forward(req, resp);
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteManufacturer(@PathVariable("manufId") Long manufId) {
        Manufacturer manufacturer = this.manufacturerService.getById(manufId);

        if (manufacturer == null) {
            return "error";
        }

        this.manufacturerService.delete(manufId);

        return "manufacts";
    }

//    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idParam = req.getParameter(ID_PARAM);
//        LOGGER.info("id = " + idParam);
//        if (StringUtils.isBlank(idParam)) {
//            forwardToErrorPage(idParam, req, resp);
//            return;
//        }
//        try {
//            long id = Long.parseLong(idParam);
//            Manufacturer manufacturer = manufacturerDAO.getById(id);
//            manufacturerDAO.delete(manufacturer);
//            forwardToManufs(req, resp);
//        } catch (Exception e) {
//            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
//            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
//        }
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (manufacturer == null) {
            return "error";
        }

        this.manufacturerService.save(manufacturer);
//        headers.setLocation(builder.path("/api/manufs/{manufId}").buildAndExpand(manufacturer.getId()).toUri());
        return "manufacts";
    }

//    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter(NAME_PARAM);
//        if (StringUtils.isBlank(name)) {
//            manufacturerDAO.save(new Manufacturer(name));
//        }
//        forwardToManufs(req, resp);
//    }

    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (manufacturer == null) {
            return "error";
        }

        this.manufacturerService.update(manufacturer);
//        headers.setLocation(builder.path("/api/manufs/{manufId}").buildAndExpand(manufacturer.getId()).toUri());
        return "manufacts";
    }

//    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter(NAME_PARAM);
//        LOGGER.info("name = " + name);
//        if (StringUtils.isBlank(name)) {
//            forwardToErrorPage(name, req, resp);
//            return;
//        }
//        String idParam = req.getParameter(ID_PARAM);
//        LOGGER.info("id = " + idParam);
//        if (StringUtils.isBlank(idParam)) {
//            forwardToErrorPage(idParam, req, resp);
//            return;
//        }
//        try {
//            long id = Long.parseLong(idParam);
//            Manufacturer manufacturer = manufacturerDAO.getById(id);
//            manufacturer.setNameManufact(name);
//            manufacturerDAO.update(manufacturer);
//            forwardToManufs(req, resp);
//        } catch (Exception e) {
//            req.setAttribute(ERR_MESSAGE_ATTR, e.getMessage());
//            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
//        }
//    }
}
