package edu.mumsched.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MumschedRepository extends JpaRepository<MumSchedUser, Long> {
	MumSchedUser findOneByEmail(String email);
}