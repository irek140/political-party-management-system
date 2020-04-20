import { Component, OnInit, Input } from '@angular/core';
import { Survey7 } from 'src/app/model/surveys/Survey7';
import { SurveyService } from 'src/app/services/survey.service';
import { SurveysListComponent } from '../surveys-list/surveys-list.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-survey7-details',
  templateUrl: './survey7-details.component.html',
  styleUrls: ['./survey7-details.component.css']
})
export class Survey7DetailsComponent implements OnInit {

  @Input()
  survey7: Survey7;

  hidden: boolean;

  constructor(private surveyService: SurveyService, private surveysList: SurveysListComponent, private toastr: ToastrService) { }

  ngOnInit() {
    this.hidden = false;
    console.log(this.survey7.id + ' ' + this.survey7.question);
  }

  chooseOption1() {
    this.surveyService.vote1Survey7(this.survey7.id).subscribe(
      data => {
        console.log(data);
        console.log(this.survey7.id);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption2() {
    this.surveyService.vote2Survey7(this.survey7.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption3() {
    this.surveyService.vote3Survey7(this.survey7.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption4() {
    this.surveyService.vote4Survey7(this.survey7.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption5() {
    this.surveyService.vote5Survey7(this.survey7.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption6() {
    this.surveyService.vote6Survey7(this.survey7.id).subscribe(
      data => {
        console.log(data);
        this.showSuccessAlert();
        this.hidden = true;
      },
      error => console.log(error)
    );
  }

  chooseOption7() {
    this.surveyService.vote7Survey7(this.survey7.id).subscribe(
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
