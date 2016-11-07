package com.longngohoang.twitter.mobile.ui.resume.adapter.viewmodel;

import com.longngohoang.twitter.mobile.ui.resume.github.model.GitRepo;
import com.longngohoang.twitter.mobile.ui.resume.adapter.vmfactory.ResumeHolderFactory;

import java.io.Serializable;

/**
 * Created by Long on 11/2/2016.
 */

public class RepoVM implements Serializable,ResumeVisitable {

    public final GitRepo gitRepo;

    public RepoVM(GitRepo gitRepo) {
        this.gitRepo = gitRepo;
    }

    @Override
    public int getVMType(ResumeHolderFactory resumeHolderFactory) {
        return resumeHolderFactory.getType(this);
    }
}
