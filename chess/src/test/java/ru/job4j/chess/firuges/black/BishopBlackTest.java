package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    void whenA1ThenA1() {
        Cell currentPosition = Cell.A1;
        Figure bishop = new BishopBlack(currentPosition);
        Cell output = bishop.position();
        assertThat(currentPosition).isEqualTo(output);
    }

    @Test
    void whenC1toG5ThenD2E3F4G5() {
        Cell start = Cell.C1;
        Cell dest = Cell.G5;
        Figure bishop = new BishopBlack(start);
        Cell[] output = bishop.way(dest);
        Cell[] expected = new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(output).isEqualTo(expected);
    }

    @Test
    void whenNotDiagonalThenImpossibleMoveException() {
        Cell start = Cell.C1;
        Cell dest = Cell.G6;
        Figure bishop = new BishopBlack(start);
        ImpossibleMoveException expected = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishop.way(dest);
                }
        );
        assertThat(expected.getMessage())
                .isEqualTo("Could not way by diagonal from %s to %s", start, dest);
    }

    @Test
    void whenCopyA2ThenPositionA2() {
        Cell start = Cell.A1;
        Cell dest = Cell.A2;
        Figure bishopStart = new BishopBlack(start);
        Figure bishopDest = bishopStart.copy(dest);
        assertThat(dest).isEqualTo(bishopDest.position());
    }
}