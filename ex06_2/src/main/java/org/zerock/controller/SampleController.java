package org.zerock.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController
{
    /*
     *      문자열 반환 
     */
    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText()
    {
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
        
        return "안녕하세요";
    }
    
    
    /*
     *      JSON, XML 반환
     *      /sample/getSample > xml 반환
     *      /sample/getSample.json > json 반환     
     */
    @GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                                                 MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample()
    {
        return new SampleVO(112, "스타", "로드");
    }
    
    /*
     *      produces 없이도 작성 가능
     */
    @GetMapping(value = "/getSample2")
    public SampleVO getSample2()
    {
        return new SampleVO(113, "로켓", "라쿤");
    }
    
    /*
     *      Collection 반환
     *      /sample/getX > xml 반환
     *      /sample/getX.json > json 반환
     */
    @GetMapping(value = "/getList")
    public List<SampleVO> getList()
    {
        return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + " First", i + " Last"))
                .collect(Collectors.toList());
    }
    @GetMapping(value = "/getMap")
    public Map<String, SampleVO> getMap()
    {
        Map<String, SampleVO> map = new HashMap<String, SampleVO>();
        map.put("First", new SampleVO(111, "그루트", "주니어"));
        
        return map;
    }
    
    /*
     *      ResponseEntitiy는 데이터와 함께 HTTP 헤더의 상태 메시지 등을 같이 전달
     */
    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight)
    {
        SampleVO vo = new SampleVO(0, "" + height, "" + weight);
        
        ResponseEntity<SampleVO> result = null;
        
        if(height < 150)
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        else
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        
        return result;
    }
    
    /*
     *      @PathVariable
     */
    @GetMapping("/product/{cat}/{pid}")
    public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid)
    {
        return new String[] {"category: " + cat, "productid: " + pid};
    }
    
    /*
     *      @RequestBody
     *      요청한 내용을 처리하기 때문에 postmapping 사용
     */
    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket)
    {
        log.info("convert..............ticket" + ticket);
        
        return ticket;
    }
    
}
