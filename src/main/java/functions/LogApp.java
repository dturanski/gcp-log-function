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

import java.util.Map;
import java.util.function.Function;

import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author David Turanski
 **/
@SpringBootApplication
public class LogApp {

	@Bean
	public Function<User, String> printUser() {
		return user -> {
			System.out.println(user);
			return user.toString();
		};
	}


	public static class User {

		public User(String userid, String changedate) {
			this.userid = userid;
			this.changedate = changedate;
		}

		public User() {
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getChangedate() {
			return changedate;
		}
		public void setChangedate(String changedate) {
			this.changedate = changedate;
		}
		@Override
		public String toString() {
			return "User{" +
				"userid='" + userid + '\'' +
				", changedate='" + changedate + '\'' +
				'}';
		}
		private String userid;
		private String changedate;

	}


	public static void main(String[] args) {
		SpringApplication.run(LogApp.class, args);
	}

}
