package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Тесты на метод подсчета количества подходящих по условию узлов")
public class PartitionByTests extends AbstractHomeWorkTest<Integer> {
    HomeWork objectToTest = new HomeWork();


    @ParameterizedTest
    @MethodSource("source")
    @DisplayName("Разные вариации NodeList")
    void nodeListCombinations(SuccessPartitionByTestObject testObject) {
        assertEquals(testObject.simpleInt, objectToTest.partitionBy(testObject.list, testObject.pred));
    }

    @Test
    @DisplayName("Негативный тест - в метод передался predicate равный null")
    void predicateError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> objectToTest.partitionBy(new Node<>(4), null),
                "pred не должен быть равен null"
        );
    }

    private List<SuccessPartitionByTestObject> source() {
        return List.of(
                new SuccessPartitionByTestObject(
                        getNodeList(new Integer[]{1, 2, 3, 4, 5}),
                        x -> x < 4,
                        3
                ),
                new SuccessPartitionByTestObject(
                        getNodeList(new Integer[]{1, 2, 3, 4, 5}),
                        x -> x < 0,
                        0
                ),
                new SuccessPartitionByTestObject(
                        getNodeList(new Integer[]{1, 2, 3, 4, 5}),
                        x -> x < 10,
                        5
                ),
                new SuccessPartitionByTestObject(
                        null,
                        x -> x > 0,
                        0
                ),
                new SuccessPartitionByTestObject(
                        getNodeList(new Integer[]{1}),
                        x -> x > 1000,
                        0
                ),
                new SuccessPartitionByTestObject(
                        getNodeList(new Integer[]{1}),
                        x -> x <= 1,
                        1
                )

        );
    }

    /**
     * Класс для описания позитивных тест кейсов
     */
    private static class SuccessPartitionByTestObject extends SuccessTestObject<Integer> {
        Predicate<Integer> pred;

        public SuccessPartitionByTestObject(Node<Integer> list, Predicate<Integer> pred, int result) {
            super(list, result);
            this.pred = pred;
        }
    }
}
