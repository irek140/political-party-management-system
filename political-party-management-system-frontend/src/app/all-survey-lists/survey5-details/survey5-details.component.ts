import { Component, OnInit, Input } from '@angular/core';
import { Survey5 } from 'src/app/model/surveys/Survey5';
import { SurveyService } from 'src/app/services/survey.service';
import { SurveysListComponent } from '../surveys-list/surveys-list.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-survey5-details',
  templateUrl: './survey5-details.component.html',
  styleUrls: ['./survey5-details.component.css']
})
export class Survey5DetailsComponent implements OnInit {

  @Input()
  survey5: Survey5;

  hidden: boolean;

  constructor(private surveyService: SurveyService, private surveysList: SurveysListComponent, private toastr: ToastrService) { }

  ngOnInit() {
    this.hidden = false;
    console.log(this.survey5.id + ' ' + this.survey5.question);
  }

  chooseOption1() {
    this.surveyService.vote1Survey5(this.survey5.id).subscribe(
      data => {
        console.log(data);
        console.log(this.survey5.id);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption2() {
    this.surveyService.vote2Survey5(this.survey5.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption3() {
    this.surveyService.vote3Survey5(this.survey5.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption4() {
    this.surveyService.vote4Survey5(this.survey5.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption5() {
    this.surveyService.vote5Survey5(this.survey5.id).subscribe(
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
