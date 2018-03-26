package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.model.Manufacturer;
import ua.goit.service.ManufacturerService;

import java.util.List;


@Controller
@RequestMapping(value = "/manufacts")
public class ManufacturerController extends BaseController{

    private ManufacturerService manufacturerService;
    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllManufacturers(ModelMap model) {
        List<Manufacturer> manufacturerList = this.manufacturerService.getAll();

        if (manufacturerList.isEmpty()) {
            return ERROR_JSP;
        }

        return MANUFACTS_JSP;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getManufacturer(@PathVariable("manufId") Long manufId) {
        if (manufId == null) {
            return ERROR_JSP;
        }

        Manufacturer manufacturer = this.manufacturerService.getById(manufId);

        if (manufacturer == null) {
            return ERROR_JSP;
        }

        return MANUFACTS_JSP;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteManufacturer(@PathVariable("manufId") Long manufId) {
        Manufacturer manufacturer = this.manufacturerService.getById(manufId);

        if (manufacturer == null) {
            return ERROR_JSP;
        }

        this.manufacturerService.delete(manufId);

        return MANUFACTS_JSP;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder builder) {
        if (manufacturer == null) {
            return ERROR_JSP;
        }

        this.manufacturerService.save(manufacturer);

        return MANUFACTS_JSP;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder builder) {


        if (manufacturer == null) {
            return ERROR_JSP;
        }

        this.manufacturerService.update(manufacturer);
        return MANUFACTS_JSP;
    }

}
