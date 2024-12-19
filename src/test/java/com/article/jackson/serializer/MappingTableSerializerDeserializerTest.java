package com.article.jackson.serializer;

import java.io.IOException;
import java.time.LocalDate;

import com.article.jackson.exception.MappingTableRuntimeException;
import com.article.jackson.fixtures.ContactDto;
import com.article.jackson.fixtures.ContactDtoAnnotationNotSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

	@Test
	void testToStringTryCatchIdiom() {
		try {
			new ObjectMapper().readValue(this.emarsysPayload, ContactDtoAnnotationNotSet.class);
			fail("Expected an MappingTableRuntimeException to be thrown");
		} catch (IndexOutOfBoundsException | JsonProcessingException | MappingTableRuntimeException e) {
			assertEquals(
					"Annotation @MappingTable not set at property com.article.jackson.fixtures.ContactDtoAnnotationNotSet#property",
					e.getMessage()
			);
		}
	}
}
