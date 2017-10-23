package edu.mumsched.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MumSchedService implements UserDetailsService {
	
	@Autowired
	private MumschedRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	protected void initialize() {
		save(new MumSchedUser("user", "demo", "ROLE_USER"));
		save(new MumSchedUser("admin", "admin", "ROLE_ADMIN"));
	}

	@Transactional
	public MumSchedUser save(MumSchedUser account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountRepository.save(account);
		return account;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MumSchedUser account = accountRepository.findOneByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	public void signin(MumSchedUser account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(MumSchedUser account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
	}
	
	private User createUser(MumSchedUser account) {
		return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(MumSchedUser account) {
		return new SimpleGrantedAuthority(account.getRole());
	}

}
