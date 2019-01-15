/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package functions;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import functions.LogApp.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author David Turanski
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LogTest {

	@Autowired
	@Qualifier("printUser")
	private Function<User, String> printUser;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void test() throws JsonProcessingException {

		User user = new User("123", "01/14/19");
		byte[] bytes = objectMapper.writeValueAsBytes(user);

		Reader reader = new InputStreamReader(new ByteArrayInputStream(bytes));
		Gson gson = new Gson();
		User user1 = gson.fromJson(reader, User.class);
		assertThat(user1.getUserid()).isEqualTo(user.getUserid());
		assertThat(user1.getChangedate()).isEqualTo(user.getChangedate());

		user1 = gson.fromJson(new String(bytes), User.class);
		assertThat(user1.getUserid()).isEqualTo(user.getUserid());
		assertThat(user1.getChangedate()).isEqualTo(user.getChangedate());
	}

	@Test
	@Ignore
	public void http() {
		RestTemplate restTemplate = new RestTemplate();

		User user = new User("123", "01/14/19");

		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/printUser",
			HttpMethod.POST, new HttpEntity(user), String.class);

		assertThat(responseEntity.getBody()).isEqualTo(user.toString());

	}

}
