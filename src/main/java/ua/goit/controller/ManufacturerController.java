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
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllManufacturers(ModelMap model) {
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

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteManufacturer(@PathVariable("manufId") Long manufId) {
        Manufacturer manufacturer = this.manufacturerService.getById(manufId);

        if (manufacturer == null) {
            return "error";
        }

        this.manufacturerService.delete(manufId);

        return "manufacts";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String saveManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder builder) {
        if (manufacturer == null) {
            return "error";
        }

        this.manufacturerService.save(manufacturer);

        return "manufacts";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder builder) {


        if (manufacturer == null) {
            return "error";
        }

        this.manufacturerService.update(manufacturer);
//        headers.setLocation(builder.path("/api/manufs/{manufId}").buildAndExpand(manufacturer.getId()).toUri());
        return "manufacts";
    }

}
