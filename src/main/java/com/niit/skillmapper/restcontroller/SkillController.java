package com.niit.skillmapper.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.skillmapper.model.Employee;
import com.niit.skillmapper.model.Skill;
import com.niit.skillmapper.service.SkillsService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

	@Autowired
	private SkillsService skillsService;
	
	@PostMapping(value="/add")
	public ResponseEntity<Skill> insertSkill(@RequestBody Skill skill)
	{
		if(skillsService.addSkills(skill))
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@PostMapping(value="/update")
	public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill)
	{
		if(skillsService.updateSkills(skill))
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.NOT_IMPLEMENTED);
		}
		
	}
	
	@GetMapping("/delete/{skillId}")
	public ResponseEntity<Skill> deleteSkill(@PathVariable("skillId") int skillId)
	{
		if(skillsService.deleteSkills(skillId))
		{
			return new ResponseEntity<Skill>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Skill>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{skillId}")
	public ResponseEntity<Skill> getSkillById(@PathVariable("skillId") int skillId)
	{
		Skill skill=skillsService.getSkillsById(skillId);
		if(skill!=null)
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Skill>(skill,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Skill>> getAllSkills()
	{
		List<Skill> allSkills=skillsService.getAllSkills();
		if(allSkills!=null)
		{
			return new ResponseEntity<List<Skill>>(allSkills,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Skill>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value="/allEmployees/{skillName}")
	public ResponseEntity<List<Employee>> getAllEmployeesBySkillName(@PathVariable("skillName") String skillName)
	{
		List<Employee> allEmployees=skillsService.getAllEmployeesBySingleSkillName(skillName);
		if(allEmployees!=null)
		{
			return new ResponseEntity<List<Employee>>(allEmployees,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/skills/{empId}")
	public ResponseEntity<List<Skill>> getAllSkillsByEmployeeId(@PathVariable("empId") int employeeId)
	{
		List<Skill> allSkills=skillsService.getAllSkillsByEmployeeId(employeeId);
		if(allSkills!=null)
		{
			return new ResponseEntity<List<Skill>>(allSkills,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Skill>>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/employee/{skillnames}")
	public ResponseEntity<List<Employee>> getAllSkillsBymultipleSkills(@PathVariable("skillnames") String skillnames)
	{
		List<Employee> allEmployees=skillsService.getAllEmployeesByMultipleSkillName(skillnames);
		if(allEmployees!=null)
		{
			return new ResponseEntity<List<Employee>>(allEmployees,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
	}
}
