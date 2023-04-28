package com.kh.learnovation.domain.user.service;

import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getUserList(Integer pageNum);

    Integer[] getPageList(Integer pageNum);

    List<UserDTO> searchUsers(String keyword);

    UserDTO getPost(Long id);

    void deleteByIdIn(List<Long> selectedIds);

    Long savePost(UserDTO userDTO);

    /** 승현 파트 */
    User 회원찾기(String username);
    void 회원가입(User user);
    public void 회원수정(User user);
}
/**
 * 나중에 ServiceImpl.java에 아래 코드 넣어 왜? 인터페이스 없이 Service 구현 클래스 작성했기 때문
 *        @Autowired
 *    private UserRepository userRepository;
 *
 *    @Autowired
 *    private BCryptPasswordEncoder encoder;
 *
 *    @Transactional(readOnly = true)
 * 	public User 회원찾기(String username) {
 *
 * 		User user = userRepository.findByUsername(username).orElseGet(()->{
 * 			return new User();
 *        });
 * 		return user;
 *    }
 *
 *    @Transactional
 *    public void 회원가입(User user) {
 * 		String rawPassword = user.getPassword(); // 1234 원문
 * 		String encPassword = encoder.encode(rawPassword); // 해쉬
 * 		user.setPassword(encPassword);
 * 		user.setRole(RoleType.USER);
 * 		userRepository.save(user);
 *    }
 *
 *    @Transactional
 *    public void 회원수정(User user) {
 * 		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
 * 		// select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
 * 		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요.
 * 		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
 * 			return new IllegalArgumentException("회원 찾기 실패");
 *        });
 *
 * 		// Validate 체크 => oauth 필드에 값이 없으면 수정 가능
 * 		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
 * 		String rawPassword = user.getPassword();
 * 		String encPassword = encoder.encode(rawPassword);
 * 		persistance.setPassword(encPassword);
 * 		persistance.setEmail(user.getEmail());
 *        }
 *
 *
 * 		// 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됩니다.
 * 		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
 *    }
 * */