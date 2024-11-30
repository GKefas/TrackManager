package com.gkefas.trackmanager.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * A custom Hibernate {@link PhysicalNamingStrategy} implementation that converts database names
 * from snake_case to UpperCamelCase.
 * <p>This strategy is applied to the physical names of catalog, schema, tables, sequences, and columns.</p>
 * <p>It ensures that identifiers in the database are mapped to UpperCamelCase.</p>
 *
 * @see PhysicalNamingStrategy
 */
public class UpperCamelCaseNamingStrategy implements PhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return formatIdentifier(name);
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
		return formatIdentifier(name);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		return formatIdentifier(name);
	}

	/**
	 * Formats the given identifier from snake_case to UpperCamelCase.
	 *
	 * @param identifier the identifier to be formatted.
	 * @return a formatted {@link Identifier} in UpperCamelCase.
	 */
	private Identifier formatIdentifier(Identifier identifier) {
		String formatted = identifier.getText();

		// Convert snake_case to UpperCamelCase
		formatted = convertSnakeToCamelCase(formatted);

		// Capitalize the first letter if needed
		if (!formatted.isEmpty())
			formatted = Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);

		return Identifier.toIdentifier(formatted);
	}

	/**
	 * Converts a string from snake_case to UpperCamelCase.
	 * <p>For example, "artist_id" becomes "ArtistId".</p>
	 *
	 * @param name the snake_case string.
	 * @return the string converted to UpperCamelCase.
	 */
	private String convertSnakeToCamelCase(String name) {
		StringBuilder result = new StringBuilder();
		String[] parts = name.split("_");

		for (String part : parts) {
			if (!part.isEmpty()) {
				result.append(part.substring(0, 1).toUpperCase()) // Capitalize first letter of each part
						.append(part.substring(1).toLowerCase()); // Convert remaining letters to lowercase
			}
		}
		return result.toString();
	}
}
