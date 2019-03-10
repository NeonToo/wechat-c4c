package com.sap.csc.web.controller.party;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.dto.request.c4c.C4CUserRequest;
import com.sap.csc.domain.model.dto.response.party.user.C4CUserResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;
import com.sap.csc.service.party.C4CUserService;
import com.sap.csc.web.controller.GeneralController;

@RestController
@RequestMapping("/users")
public class C4CUserController extends GeneralController {
	
	private final C4CUserService c4CUserService;

	@Autowired
	public C4CUserController(C4CUserService c4CUserService) {
		this.c4CUserService = c4CUserService;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping
	public Collection<C4CUserResponse> findAllByOpenID() {
		Optional<String> openID = super.getCurrentUserOpenID();
		
		if(openID.isPresent()) {
			List<C4CUser> c4cUsers = c4CUserService.findAllByOpenID(openID.get());
			
			if (CollectionUtils.isNotEmpty(c4cUsers)) {
				return c4cUsers.stream().map(c4cUser -> new C4CUserResponse(c4cUser)).collect(Collectors.toList());
			}
		}
		
		return CollectionUtils.EMPTY_COLLECTION;
	}
	
	@PostMapping
	public C4CUserResponse bindC4CUser(@RequestBody C4CUserRequest c4cUserRequest) {
		Optional<String> originID = super.getCurrentOriginID();
		Optional<String> openID = super.getCurrentUserOpenID();
		
		if(originID.isPresent() && openID.isPresent()) {
			Optional<C4CUser> c4cUser = c4CUserService.bindC4CUser(originID.get(), openID.get(), c4cUserRequest);
			
			if(c4cUser.isPresent()) {
				return new C4CUserResponse(c4cUser.get());
			}
		}
		
		return null;
	}
	
	@GetMapping("/{id}")
	public C4CUserResponse findOne(@PathVariable Long id) {
		Optional<C4CUser> c4cUser = c4CUserService.findOne(id);
		
		if(c4cUser.isPresent()) {
			return new C4CUserResponse(c4cUser.get());
		}
		
		return null;
	}
	
	@PutMapping("/{id}")
	public C4CUserResponse updateC4CUser(@PathVariable Long id, @RequestBody C4CUserRequest c4cUserRequest) {
		Optional<C4CUser> c4cUser = c4CUserService.updateC4CUser(id, c4cUserRequest);
		
		if(c4cUser.isPresent()) {
			return new C4CUserResponse(c4cUser.get());
		}
		
		return null;
	}
	
	@PatchMapping("/{id}")
	public C4CUserResponse changePriamryStatus(@PathVariable Long id) {
		Optional<C4CUser> c4cUser = c4CUserService.changePriamryStatus(id);
		
		if(c4cUser.isPresent()) {
			return new C4CUserResponse(c4cUser.get());
		}
		
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteC4CUser(@PathVariable Long id) {
		c4CUserService.deleteC4CUser(id);
	}

}
