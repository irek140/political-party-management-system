import { Component, OnInit, Input } from '@angular/core';
import { Survey6 } from 'src/app/model/surveys/Survey6';
import { SurveyService } from 'src/app/services/survey.service';
import { SurveysListComponent } from '../surveys-list/surveys-list.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-survey6-details',
  templateUrl: './survey6-details.component.html',
  styleUrls: ['./survey6-details.component.css']
})
export class Survey6DetailsComponent implements OnInit {

  @Input()
  survey6: Survey6;

  hidden: boolean;

  constructor(private surveyService: SurveyService, private surveysList: SurveysListComponent, private toastr: ToastrService) { }

  ngOnInit() {
    this.hidden = false;
    console.log(this.survey6.id + ' ' + this.survey6.question);
  }

  chooseOption1() {
    this.surveyService.vote1Survey6(this.survey6.id).subscribe(
      data => {
        console.log(data);
        console.log(this.survey6.id);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption2() {
    this.surveyService.vote2Survey6(this.survey6.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption3() {
    this.surveyService.vote3Survey6(this.survey6.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption4() {
    this.surveyService.vote4Survey6(this.survey6.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption5() {
    this.surveyService.vote5Survey6(this.survey6.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption6() {
    this.surveyService.vote6Survey6(this.survey6.id).subscribe(
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
