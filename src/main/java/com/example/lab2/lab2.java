package com.example.lab2;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class lab2 {

    @Controller
    public static class lab {
        @RequestMapping("/hello")
        @ResponseBody
        public String hello() {
            return "hello Arafat";
        }

        @RequestMapping("/currentTime")
        @ResponseBody
        public String currentTime() {
//            return "Current Time" + "\n" + java.time.LocalDateTime.now();
            Instant day = Instant.parse("2005-04-26T00:00:00Z");
            Instant now = Instant.now();
            Duration dur = Duration.between(day, now);
            long days = dur.toDays();
            return "after" + "\n" + days + "\n" + "day";
        }

        @RequestMapping("/api")
        @ResponseBody
        public String api(@RequestParam(value = "q") int q) {
            StringBuilder num = new StringBuilder();
            for (int i = 1; i <= q; i++) {
                num.append(i);
            }
            return num.toString();
        }

        @RequestMapping("/random_number")
        @ResponseBody
        public int randomNumber() {
            Random random = new Random();
            return random.nextInt(500);
        }

        @RequestMapping("/fibNum")
        @ResponseBody
        public int[] fibNumber(@RequestParam int n) {
            return fibNum2(n);
        }

        public int fibNum(int num) {
            if (num == 0) return 0;
            int fibNum1 = 0;
            int fibNum2 = 1;
            for (int i = 2; i < num; i++) {
                int fibNum3 = fibNum1 + fibNum2;
                fibNum1 = fibNum2;
                fibNum2 = fibNum3;
            }
            return fibNum2;
        }

        public int[] fibNum2(int num) {
            if(num == 0) return new int[]{0};
            if(num == 1) return new int[]{0, 1};

            int[] fibNum = new int[num];
            fibNum[0] = 0;
            fibNum[1] = 1;
            for (int i = 2; i < num; i++) {
                fibNum[i] = fibNum[i - 1] + fibNum[i - 2];
            }
            return fibNum;
        }

        @RequestMapping("/word")
        @ResponseBody
        public String reverse(@RequestParam String word) {
            StringBuilder reversed = new StringBuilder(word);
            reversed.reverse();
            return reversed.toString();
        }

        private final int X_num = new Random().nextInt(1000) + 1;
        @PostConstruct
        @ResponseBody
        public void init(){
            System.out.println("Число "+ X_num);
        }
        @RequestMapping("/guessAPI")
        @ResponseBody
        public String guessNum(@RequestParam(value = "number") int num) {
            if (num == X_num) {
                return "Вы верно угадали число!";
            } else if (num < X_num) {
                return "Неверно, выше число меньше загаданного числа!";
            } else {
                return "Неверно, ваше число больше загаданного числа!";
            }
        }
    }
}

