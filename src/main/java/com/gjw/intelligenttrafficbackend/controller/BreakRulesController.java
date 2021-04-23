package com.gjw.intelligenttrafficbackend.controller;
import com.gjw.intelligenttrafficbackend.entity.BreakRules;
import com.gjw.intelligenttrafficbackend.service.BreakRulesService;
import com.gjw.intelligenttrafficbackend.utils.R;
import com.gjw.intelligenttrafficbackend.utils.ResultCode;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-22
 * 交通违规controller
 */
@RestController
@RequestMapping("/breakRules")
public class BreakRulesController {


    @Resource
    private BreakRulesService breakRulesService;

    @GetMapping("list")
    public R listAllBreakRules() {
        List<BreakRules> breakRulesList = breakRulesService.list();
        if (!CollectionUtils.isEmpty(breakRulesList)) {
            return R.ok().messageAndCode(ResultCode.FIND_ALL_BREAK_RULES_SUCCESS).data("breakRulesList", breakRulesList);
        } else {
            return R.error().messageAndCode(ResultCode.FIND_ALL_BREAK_RULES_FAIL);
        }
    }
}

