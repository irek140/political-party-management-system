import { Component, OnInit, Input } from '@angular/core';
import { Survey3 } from 'src/app/model/surveys/Survey3';
import { SurveyService } from 'src/app/services/survey.service';
import { SurveysListComponent } from '../surveys-list/surveys-list.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-survey3-details',
  templateUrl: './survey3-details.component.html',
  styleUrls: ['./survey3-details.component.css']
})
export class Survey3DetailsComponent implements OnInit {

  @Input()
  survey3: Survey3;

  hidden: boolean;

  constructor(private surveyService: SurveyService, private surveysList: SurveysListComponent, private toastr: ToastrService) { }

  ngOnInit() {
    this.hidden = false;
    console.log(this.survey3.id + ' ' + this.survey3.question);
  }

  chooseOption1() {
    this.surveyService.vote1Survey3(this.survey3.id).subscribe(
      data => {
        console.log(data);
        console.log(this.survey3.id);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption2() {
    this.surveyService.vote2Survey3(this.survey3.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption3() {
    this.surveyService.vote3Survey3(this.survey3.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  showSuccessAlert() {
    this.toastr.success('Dziękujemy za oddanie głosu!', 'SUKCES!');
  }
}
