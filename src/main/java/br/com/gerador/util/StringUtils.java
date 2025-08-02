package br.com.gerador.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

  /**
   * Converte uma string em camelCase para snake_case.
   *
   * @param input A string em camelCase.
   * @return A string convertida para snake_case.
   */
  public static String convertToSnakeCase(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }
    StringBuilder result = new StringBuilder();
    for (char c : input.toCharArray()) {
      if (Character.isUpperCase(c)) {
        if (result.length() > 0) {
          result.append('_');
        }
        result.append(Character.toLowerCase(c));
      } else {
        result.append(c);
      }
    }
    return result.toString();
  }

}
