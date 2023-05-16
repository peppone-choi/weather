package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }
    @ApiOperation(value = "일기 텍스트와 날씨를 이용하여 DB에 일기를 저장 합니다.")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam
                     @ApiParam(value = "일기를 쓸 날짜", example = "2023-05-16")
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @ApiParam(value = "일기 텍스트")
                     @RequestBody
                     String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam
                          @ApiParam(value = "선택 할 날짜", example = "2023-05-16")
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간중의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam
                            @ApiParam(value = "조회할 기간의 첫번째날", example = "2023-05-16")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            LocalDate startDate,
                            @RequestParam
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 마지막날", example = "2023-05-16") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 가장 첫번째 일기를 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam
                     @ApiParam(value = "선택 할 날짜", example = "2023-05-16")
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam
                     @ApiParam(value = "선택 할 날짜", example = "2023-05-16")
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     LocalDate date){
        diaryService.deleteDiary(date);
    }

}
