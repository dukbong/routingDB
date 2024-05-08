package com.example.routingDB.connecting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.routingDB.dto.UserDbDTO;
import com.example.routingDB.entity.UserDB;
import com.example.routingDB.entity.UserEntity;
import com.example.routingDB.repository.UserDBRepository;
import com.example.routingDB.repository.UserEntityRepository;
import com.example.routingDB.serviceImpl.ConnectingServiceImpl;

@ExtendWith(SpringExtension.class)
public class ConnectingServiceTest {

	@Mock
	private UserEntityRepository userEntityRepository;

	@Mock
	private UserDBRepository userDBRepository;

	@InjectMocks
	private ConnectingServiceImpl connectingServiceImpl;

	@Test
	public void testSaveDbInfo() {
		UserEntity userEntity = UserEntity.builder().id(1L).userName("testUser").userPassword("password").build();

		UserDbDTO userDbDTO = UserDbDTO.builder().createUser("testUser").databaseName("testDB")
				.url("jdbc:test://localhost:3306/testDB").driverName("com.test.Driver").userName("dbUser")
				.password("dbPassword").build();

		when(userEntityRepository.findByUserName("testUser")).thenReturn(Optional.of(userEntity));

		connectingServiceImpl.saveDbInfo(userDbDTO);

		ArgumentCaptor<UserDB> userDbCaptor = ArgumentCaptor.forClass(UserDB.class);
		verify(userDBRepository, times(1)).save(userDbCaptor.capture());

		UserDB savedUserDB = userDbCaptor.getValue();
		assertThat(savedUserDB).isNotNull();
		assertThat(savedUserDB.getDatabaseName()).isEqualTo("testDB");
		assertThat(savedUserDB.getUrl()).isEqualTo("jdbc:test://localhost:3306/testDB");
		assertThat(savedUserDB.getDriverName()).isEqualTo("com.test.Driver");
		assertThat(savedUserDB.getUsername()).isEqualTo("dbUser");
		assertThat(savedUserDB.getPassword()).isEqualTo("dbPassword");

		assertThat(savedUserDB.getUserEntity()).isEqualTo(userEntity);
		assertThat(userEntity.getUserDBs()).contains(savedUserDB);
		assertThat(userEntity.getUserDBs().size()).isEqualTo(1);
		assertThat(userEntity.getUserDBs().get(0).getDatabaseName()).isEqualTo("testDB");
	}

	@Test
	public void testSaveDbInfo_duplicateDatabase() {
		UserEntity userEntity = UserEntity.builder().id(1L).userName("testUser").userPassword("password").build();

		userEntity.getUserDBs()
				.add(UserDB.builder().id(1L).databaseName("duplicateDB").url("jdbc:test://localhost:3306/duplicateDB")
						.driverName("com.test.Driver").username("dbUser").password("dbPassword").build());

		UserDbDTO userDbDTO = UserDbDTO.builder().createUser("testUser").databaseName("duplicateDB")
				.url("jdbc:test://localhost:3306/duplicateDB").driverName("com.test.Driver").userName("dbUser")
				.password("dbPassword").build();

		when(userEntityRepository.findByUserName("testUser")).thenReturn(Optional.of(userEntity));

		assertThatThrownBy(() -> connectingServiceImpl.saveDbInfo(userDbDTO)).isInstanceOf(DuplicateKeyException.class)
				.hasMessage("Duplicate DB key");

		verify(userDBRepository, times(0)).save(Mockito.any(UserDB.class));
	}

}
