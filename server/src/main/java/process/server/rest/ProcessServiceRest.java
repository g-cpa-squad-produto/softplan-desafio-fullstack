package process.server.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import process.server.apis.ProcessService;
import process.server.domain.Process;
import process.server.domain.dto.ProcessDTO;

@RestController
@RequestMapping(value="api/process")
public class ProcessServiceRest {
	
	@Autowired
	private ProcessService processService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "", method = RequestMethod.POST)
    private ProcessDTO save(@RequestBody  ProcessDTO processDTO) {
		return processService.save(processDTO);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "", method = RequestMethod.GET)
    private List<Process> findAll() {
		return processService.findAll();
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
    private Map<String, Object> findAllWithPagination(@RequestParam(value = "sort", required = false) String sort, 
    		@RequestParam("page") Long page, 
    		@RequestParam("per_page") Long perPage,
    		@RequestParam(value = "filter", required = false) String filter) {
		return processService.findAllWithPagination(sort, page, perPage, filter);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ProcessDTO findOne(@PathVariable("id") Long id) {
		return processService.findOne(id);
    }
	
}
