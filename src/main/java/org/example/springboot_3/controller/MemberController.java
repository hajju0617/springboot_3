package org.example.springboot_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot_3.dto.MemberForm;
import org.example.springboot_3.entity.Member;
import org.example.springboot_3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepositoryepository;

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());

        Member member = memberForm.toEntity();
        log.info(member.toString());

        Member saved = memberRepositoryepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepositoryepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        List<Member> memberEntityList = memberRepositoryepository.findAll();
        model.addAttribute("memberList", memberEntityList);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepositoryepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);

        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        Member memberEntity = form.toEntity();
        Member result = memberRepositoryepository.findById(memberEntity.getId()).orElse(null);
        if (result != null) {
            memberRepositoryepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Member result = memberRepositoryepository.findById(id).orElse(null);
        if (result != null) {
            memberRepositoryepository.delete(result);
            redirectAttributes.addFlashAttribute("msg", "삭제 완료");
        }
        return "redirect:/members";
    }
}
