package com.dms.recieveClient.urlRepository;

import com.dms.recieveClient.model.Profile;

import java.util.List;

public interface ProfileRepository {
	List<Profile> getAllProfiles();
	Profile getProfile(String userId);

}