package dev.zecovery.snaspe.userlocation.presentation.presenter.control;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.commons.control.domain.usecase.SaveControlListUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveControlListContract;

public class SaveControlListPresenter implements SaveControlListContract.Presenter {

    private SaveControlListContract.View view;
    private SaveControlListUseCase saveControlListUseCase;

    @Inject
    public SaveControlListPresenter(SaveControlListUseCase saveControlListUseCase) {
        this.saveControlListUseCase = saveControlListUseCase;
    }

    @Override
    public void initialize(SaveControlListContract.View view) {
        this.view = view;
    }

    @Override
    public void saveControlList(List<Control> controlList) {
        try {
            saveControlListUseCase.setData(controlList).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onControlListSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onControlListSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onControlListSavedFailure(e.getMessage());
        }
    }
}
