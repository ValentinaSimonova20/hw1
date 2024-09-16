package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Тесты на метод поиска элемента на позиции N в односвязном списке")
public class FindNthElementTests extends AbstractHomeWorkTest<String> {

    HomeWork objectToTest = new HomeWork();

    @ParameterizedTest
    @MethodSource("successSource")
    @DisplayName("Разные вариации NodeList для поиска элемента в list-е (findNthElement)")
    void nodeListCombinations(SuccessFindNthElemTestObject testObject) {
        assertEquals(testObject.result, objectToTest.findNthElement(testObject.list, testObject.simpleInt));
    }


    @ParameterizedTest
    @DisplayName("Негативныe тесты")
    @MethodSource("errorSource")
    void negativeTest(NegativeTestObject negativeTestObject) {
        assertThrows(
                IllegalArgumentException.class,
                negativeTestObject.executable,
                negativeTestObject.errorMessage
        );
    }

    /**
     * @return Позитивные тест кейсы
     */
    private List<SuccessFindNthElemTestObject> successSource() {
        return List.of(
                new SuccessFindNthElemTestObject(
                        getNodeList(new String[]{"java", "kotlin", "pascal", "basic"}),
                        "kotlin",
                        1
                ),
                new SuccessFindNthElemTestObject(
                        getNodeList(new String[]{"java", "kotlin", "pascal", "basic"}),
                        "basic",
                        3
                ),
                new SuccessFindNthElemTestObject(
                        getNodeList(new String[]{"one"}),
                        "one",
                        0
                )

        );
    }

    /**
     * @return Негативные тест кейсы
     */
    private List<NegativeTestObject> errorSource() {
        String INCORRECT_N_MESSAGE = "Передан невалидный аргумент n. " +
                "n должен быть больше либо равен нулю, но меньше длины list";
        return List.of(
                new NegativeTestObject(
                        () -> objectToTest.findNthElement(null, 0),
                        "list не должен быть равен null"
                ),
                new NegativeTestObject(
                        () -> objectToTest.findNthElement(getNodeList(new String[]{"some", "word"}), 2),
                        INCORRECT_N_MESSAGE
                ),
                new NegativeTestObject(
                        () -> objectToTest.findNthElement(getNodeList(new String[]{"some", "word"}), -100),
                        INCORRECT_N_MESSAGE
                )

        );
    }


    /**
     * Класс для описания позитивных тест кейсов метода поиска элемента на позиции pos в односвязном списке
     */
    private static class SuccessFindNthElemTestObject extends SuccessTestObject<String> {
        String result;

        /**
         *
         * @param list - односвязный список с заполненными элементами
         * @param result - результат выполнения функции - элемент на позиции pos
         * @param pos - позиция с которой надо вернуть элемент
         */
        public SuccessFindNthElemTestObject(Node<String> list, String result, int pos) {
            super(list, pos);
            this.result = result;
        }
    }

    /**
     * Класс для описания негативных тест кейсов
     */
    private static class NegativeTestObject {
        /** операция которую необходимо выполнить для получения ошибки */
        Executable executable;
        /** сообщение об ошибке которое получим после выполнения операции executable*/
        String errorMessage;

        public NegativeTestObject(Executable executable, String errorMessage) {
            this.executable = executable;
            this.errorMessage = errorMessage;
        }
    }
}
