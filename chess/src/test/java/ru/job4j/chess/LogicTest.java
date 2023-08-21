package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenOccupiedCellException() {
        Logic logic = new Logic();
        Cell start = Cell.A1;
        Cell dest = Cell.C3;
        Figure figure = new BishopBlack(start);
        Figure secondFigure = new BishopBlack(Cell.B2);
        logic.add(figure);
        logic.add(secondFigure);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(start, dest);
        });
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    public void whenMoveThenImpossibleMoveException() {
        Logic logic = new Logic();
        Cell start = Cell.A1;
        Cell dest = Cell.C4;
        Figure figure = new BishopBlack(start);
        logic.add(figure);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(start, dest);
        });
        assertThat(exception.getMessage())
                .isEqualTo("Could not way by diagonal from %s to %s", start, dest);
    }
}