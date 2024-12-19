package com.article.jackson.serializer;

import java.io.IOException;
import java.time.LocalDate;

import com.article.jackson.fixtures.ContactDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MappingTableSerializerDeserializerTest {

	String emarsysPayload = """
			{
			    "1": "Jane",
			    "2": "Doe",
			    "3": "jane.doe@example.com",
			    "4": "1989-11-09",
			    "46": "2",
			    "100674": "1"
			}
			""";

	@Test
	void serialize() throws IOException {
		ContactDto contact = new ContactDto();
		contact.setSalutation("FEMALE");
		contact.setFirstname("Jane");
		contact.setLastname("Doe");
		contact.setEmail("jane.doe@example.com");
		contact.setBirthday(LocalDate.of(1989, 11, 9));
		contact.setMarketingInformation(true);
		String json = new ObjectMapper().writeValueAsString(contact);
		assertThatJson(this.emarsysPayload)
				.when(Option.IGNORING_ARRAY_ORDER)
				.isEqualTo(json);
	}

	@Test
	void deserialize() throws IOException {
		ContactDto contact = new ObjectMapper().readValue(this.emarsysPayload, ContactDto.class);
		assertEquals("FEMALE", contact.getSalutation());
		assertEquals("Jane", contact.getFirstname());
		assertEquals("Doe", contact.getLastname());
		assertEquals("jane.doe@example.com", contact.getEmail());
		assertEquals(LocalDate.of(1989, 11, 9), contact.getBirthday());
		assertTrue(contact.getMarketingInformation());
		assertTrue(contact.isMarketingInformation());
	}
}
