package com.example.generationuniquecode.service;

import com.example.generationuniquecode.dto.CodesDTO;
import com.example.generationuniquecode.entity.CodesEntity;
import com.example.generationuniquecode.repository.CodesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CodesServiceTest {
    @Mock
    private CodesRepository codesRepository;

    @InjectMocks
    private CodesService codesService;

    @Test
    public Boolean isNotExist(String code){
        verify(codesRepository.cntFound(code));
        return codesRepository.cntFound(code) == 0;
    }

    @Test
    public void saveCode(String pCode){
        final CodesEntity codesEntity = mock(CodesEntity.class);
        codesService.saveCode(pCode);

        codesEntity.setCode(pCode);
        verify(codesRepository.save(codesEntity));
    }

    @Test
    public String generateCode(){
        List<String> symb = Arrays.asList("a","b","c","d","e","f","g","h","i","j",
                "k","l","m","n","o","p","q","r","s","t",
                "u","v","w","x","y","z");
        List<String> numb = Arrays.asList("0","1","2","3","4","5","6","7","8","9");
        String borderCode1 = "z9z9";
        String borderCode2 = "z9";
        String preCode = null;
        String str1;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String strS;
        String strN;
        int posBeg0 = 0;
        int posEnd0 = 4;
        int posBeg = 4;
        int posEnd = 6;

        for (int i = 0; i < symb.size(); i ++){
            str1 = symb.get(i);
            for (int j = 0; j < numb.size(); j ++){
                str2 = numb.get(j);
                for (int k = 0; k < symb.size(); k ++) {
                    str3 = symb.get(k);
                    for (int l = 0; l < numb.size(); l ++) {
                        str4 = numb.get(l);
                        preCode = str1+str2+str3+str4;
                        preCode = borderCode1; //граничные значения функции генерации уникального кода
                        if (false) {
                            saveCode(preCode);
                            return preCode;
                        } else {
                            if (preCode.substring(posBeg0, posEnd0).equals(borderCode1)) {
                                for (int m = 0; m < symb.size(); m ++) {
                                    str5 = symb.get(m);
                                    for (int n = 0; n < symb.size(); n ++) {
                                        str6 = numb.get(n);
                                        preCode = preCode + str5 + str6;
                                        if (isNotExist(preCode)) {
                                            saveCode(preCode);
                                            return preCode;
                                        } else {
                                            if (preCode.substring(posBeg, posEnd).equals(borderCode2)){
                                                longLoop:
                                                for (int o = 0; o < symb.size(); o ++) {
                                                    strS = symb.get(o);
                                                    for (int p = 0; p < symb.size(); p ++) {
                                                        strN = symb.get(p);
                                                        preCode = preCode + strS + strN;
                                                        if (isNotExist(preCode)) {
                                                            saveCode(preCode);
                                                            return preCode;
                                                        } else {
                                                            posBeg = posBeg +2;
                                                            posEnd = posEnd +2;
                                                            if (preCode.substring(posBeg, posEnd).equals(borderCode2)) {
                                                                continue longLoop;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return preCode;
    }

    @Test
    public CodesDTO getNewCode() {
        CodesDTO codesDTO = mock(CodesDTO.class);
        codesDTO.setCode(generateCode());
        return codesDTO;
    }
}

