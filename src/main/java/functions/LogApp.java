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

import java.util.function.Function;

import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import io.cloudevents.impl.DefaultCloudEventImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author David Turanski
 **/
@SpringBootApplication
public class LogApp {

	@Bean
	public Function<PubsubMessage, String> log() {
		return message -> {
			ByteString result = message.getData();
			System.out.println("Received: " + result.toString());
			return result.toString();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LogApp.class, args);
	}

}
