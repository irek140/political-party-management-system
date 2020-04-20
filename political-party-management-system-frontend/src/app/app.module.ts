import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ArticleListComponent } from './article-options/article-list/article-list.component';
import { ArticleDetailsComponent } from './article-options/article-details/article-details.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule, HttpClient} from '@angular/common/http';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthenticationService } from './services/authentication.service';
import { ActivateAccountService } from './services/activate-account.service';
import { AdminPanelComponent } from './panels/admin-panel/admin-panel.component';
import { UserPanelComponent } from './panels/user-panel/user-panel.component';
import { ActivateAccountComponent } from './activate-account/activate-account.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { WriteArticleComponent } from './article-options/write-article/write-article.component';
import { SendMessageComponent } from './message-options/send-message/send-message.component';
import { UsersListComponent } from './users-list/users-list.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserService } from './services/user.service';
import { CreateTaskComponent } from './task-options/create-task/create-task.component';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { NgxEditorModule } from 'ngx-editor';
import { TaskListComponent } from './task-options/task-list/task-list.component';
import { TaskDetailsComponent } from './task-options/task-details/task-details.component';
import { MemberPanelComponent } from './panels/member-panel/member-panel.component';
import { JoinComponent } from './join-to-organisation/join/join.component';
import { JoinMessageDetailsComponent } from './join-to-organisation/join-message-details/join-message-details.component';
import { JoinMessageListComponent } from './join-to-organisation/join-message-list/join-message-list.component';
import { UnacceptableArticlesListComponent } from './article-options/unacceptable-articles-list/unacceptable-articles-list.component';
import { UnacceptableArticleDetailsComponent } from './article-options/unacceptable-article-details/unacceptable-article-details.component';
import { ArticleService } from './services/article.service';
import { TaskSentListComponent } from './task-options/task-sent-list/task-sent-list.component';
import { TaskSentDetailsComponent } from './task-options/task-sent-details/task-sent-details.component';
import { PresidentPanelComponent } from './panels/president-panel/president-panel.component';
import { VicePresidentPanelComponent } from './panels/vice-president-panel/vice-president-panel.component';
import { MatSelectModule } from '@angular/material/select';
import { SecretaryPanelComponent } from './panels/secretary-panel/secretary-panel.component';
import { RegionPresidentPanelComponent } from './panels/region-president-panel/region-president-panel.component';
import { RegionVicePresidentPanelComponent } from './panels/region-vice-president-panel/region-vice-president-panel.component';
import { ConstituencyPresidentPanelComponent } from './panels/constituency-president-panel/constituency-president-panel.component';
import { ConstituencyVicePresidentPanelComponent } from './panels/constituency-vice-president-panel/constituency-vice-president-panel.component';
import { CreateSurveyComponent } from './survey-creator/create-survey/create-survey.component';
import { EditForPresidentComponent } from './edits/edit-for-president/edit-for-president.component';
import { EditForPresidentDetailsComponent } from './edits/edit-for-president-details/edit-for-president-details.component';
import { EditForSecretaryComponent } from './edits/edit-for-secretary/edit-for-secretary.component';
import { EditForSecretaryDetailsComponent } from './edits/edit-for-secretary-details/edit-for-secretary-details.component';
import { EditForRegionPresidentComponent } from './edits/edit-for-region-president/edit-for-region-president.component';
import { EditForRegionPresidentDetailsComponent } from './edits/edit-for-region-president-details/edit-for-region-president-details.component';
import { EditForConstituencyPresidentComponent } from './edits/edit-for-constituency-president/edit-for-constituency-president.component';
import { EditForConstituencyPresidentDetailsComponent } from './edits/edit-for-constituency-president-details/edit-for-constituency-president-details.component';
import { EditForRegionSecretaryComponent } from './edits/edit-for-region-secretary/edit-for-region-secretary.component';
import { EditForConstituencySecretaryComponent } from './edits/edit-for-constituency-secretary/edit-for-constituency-secretary.component';
import { EditForRegionSecretaryDetailsComponent } from './edits/edit-for-region-secretary-details/edit-for-region-secretary-details.component';
import { EditForConstituencySecretaryDetailsComponent } from './edits/edit-for-constituency-secretary-details/edit-for-constituency-secretary-details.component';
import { RegionSecretaryPanelComponent } from './panels/region-secretary-panel/region-secretary-panel.component';
import { ConstituencySecretaryPanelComponent } from './panels/constituency-secretary-panel/constituency-secretary-panel.component';
import { MessageListComponent } from './message-options/message-list/message-list.component';
import { MessageDetailsComponent } from './message-options/message-details/message-details.component';
import { Survey2Component } from './survey-creator/survey2/survey2.component';
import { SurveysListComponent } from './all-survey-lists/surveys-list/surveys-list.component';
import { Survey2DetailsComponent } from './all-survey-lists/survey2-details/survey2-details.component';
import { Survey3Component } from './survey-creator/survey3/survey3.component';
import { Survey4Component } from './survey-creator/survey4/survey4.component';
import { Survey5Component } from './survey-creator/survey5/survey5.component';
import { Survey6Component } from './survey-creator/survey6/survey6.component';
import { Survey7Component } from './survey-creator/survey7/survey7.component';
import { Survey3DetailsComponent } from './all-survey-lists/survey3-details/survey3-details.component';
import { Survey4DetailsComponent } from './all-survey-lists/survey4-details/survey4-details.component';
import { Survey5DetailsComponent } from './all-survey-lists/survey5-details/survey5-details.component';
import { Survey6DetailsComponent } from './all-survey-lists/survey6-details/survey6-details.component';
import { Survey7DetailsComponent } from './all-survey-lists/survey7-details/survey7-details.component';
import { SurveysResultsListComponent } from './surveys-results/surveys-results-list/surveys-results-list.component';
import { Surveys2ResultsDetailsComponent } from './surveys-results/surveys2-results-details/surveys2-results-details.component';
import { Surveys3ResultsDetailsComponent } from './surveys-results/surveys3-results-details/surveys3-results-details.component';
import { Surveys4ResultsDetailsComponent } from './surveys-results/surveys4-results-details/surveys4-results-details.component';
import { Surveys5ResultsDetailsComponent } from './surveys-results/surveys5-results-details/surveys5-results-details.component';
import { Surveys6ResultsDetailsComponent } from './surveys-results/surveys6-results-details/surveys6-results-details.component';
import { Surveys7ResultsDetailsComponent } from './surveys-results/surveys7-results-details/surveys7-results-details.component';
import { ArticleRemoveComponent } from './article-options/article-remove/article-remove.component';
import { ArticleRemoveDetailsComponent } from './article-options/article-remove-details/article-remove-details.component';
import { CreateStructureComponent } from './structures/create-structure/create-structure.component';
import { RegionDetailsComponent } from './structures/region-details/region-details.component';
import { ConstituencyDetailsComponent } from './structures/constituency-details/constituency-details.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/';
import { AdminGuardService } from './services/guards/admin-guard.service';


@NgModule({
  declarations: [
    AppComponent,
    ArticleListComponent,
    ArticleDetailsComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavbarComponent,
    AdminPanelComponent,
    UserPanelComponent,
    ActivateAccountComponent,
    PageNotFoundComponent,
    WriteArticleComponent,
    SendMessageComponent,
    UsersListComponent,
    UserDetailsComponent,
    CreateTaskComponent,
    MessageListComponent,
    MessageDetailsComponent,
    TaskListComponent,
    TaskDetailsComponent,
    MemberPanelComponent,
    JoinComponent,
    JoinMessageDetailsComponent,
    JoinMessageListComponent,
    UnacceptableArticlesListComponent,
    UnacceptableArticleDetailsComponent,
    TaskSentListComponent,
    TaskSentDetailsComponent,
    EditForPresidentComponent,
    EditForPresidentDetailsComponent,
    PresidentPanelComponent,
    VicePresidentPanelComponent,
    SecretaryPanelComponent,
    EditForSecretaryComponent,
    EditForSecretaryDetailsComponent,
    RegionPresidentPanelComponent,
    RegionVicePresidentPanelComponent,
    EditForRegionPresidentComponent,
    EditForRegionPresidentDetailsComponent,
    EditForConstituencyPresidentComponent,
    EditForConstituencyPresidentDetailsComponent,
    ConstituencyPresidentPanelComponent,
    ConstituencyVicePresidentPanelComponent,
    CreateSurveyComponent,
    EditForRegionSecretaryComponent,
    EditForConstituencySecretaryComponent,
    EditForRegionSecretaryDetailsComponent,
    EditForConstituencySecretaryDetailsComponent,
    RegionSecretaryPanelComponent,
    ConstituencySecretaryPanelComponent,
    Survey2Component,
    SurveysListComponent,
    Survey2DetailsComponent,
    Survey3Component,
    Survey4Component,
    Survey5Component,
    Survey6Component,
    Survey7Component,
    Survey3DetailsComponent,
    Survey4DetailsComponent,
    Survey5DetailsComponent,
    Survey6DetailsComponent,
    Survey7DetailsComponent,
    SurveysResultsListComponent,
    Surveys2ResultsDetailsComponent,
    Surveys3ResultsDetailsComponent,
    Surveys4ResultsDetailsComponent,
    Surveys5ResultsDetailsComponent,
    Surveys6ResultsDetailsComponent,
    Surveys7ResultsDetailsComponent,
    ArticleRemoveComponent,
    ArticleRemoveDetailsComponent,
    CreateStructureComponent,
    RegionDetailsComponent,
    ConstituencyDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    BrowserModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatInputModule,
    MatCheckboxModule,
    MatSelectModule,
    MatRadioModule,
    NgxEditorModule,
    FontAwesomeModule,
    NgxChartsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ToastrModule.forRoot()
  ],
  providers: [
    HttpClient,
    AuthenticationService,
    AdminGuardService,
    ActivateAccountService,
    NavbarComponent,
    UserService,
    ArticleService,
    MatDatepickerModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
