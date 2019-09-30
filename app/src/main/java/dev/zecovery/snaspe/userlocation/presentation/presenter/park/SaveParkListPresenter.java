package dev.zecovery.snaspe.userlocation.presentation.presenter.park;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.commons.park.domain.usecase.SaveParkListUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveParkListContract;

public class SaveParkListPresenter implements SaveParkListContract.Presenter {

    private SaveParkListContract.View view;
    private SaveParkListUseCase saveParkListUseCase;

    @Inject
    public SaveParkListPresenter(SaveParkListUseCase saveParkListUseCase) {
        this.saveParkListUseCase = saveParkListUseCase;
    }

    @Override
    public void initialize(SaveParkListContract.View view) {
        this.view = view;
    }

    @Override
    public void saveParkList(List<Park> parkList) {
        try {
            saveParkListUseCase.setData(parkList).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onParkListSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onParkListSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onParkListSavedFailure(e.getMessage());
        }
    }
}
