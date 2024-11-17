package Model;

import java.util.List;

/**
 * A record representing a grid used in the Four-Square cipher.
 *
 * @param grid the grid to be used in the cipher
 *
 * @author Bartosz Pałucki
 * @version 3.0
 */
public record GridRecord(List<List<Character>> grid) {}
