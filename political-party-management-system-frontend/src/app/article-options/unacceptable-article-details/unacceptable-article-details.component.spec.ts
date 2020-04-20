import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnacceptableArticleDetailsComponent } from './unacceptable-article-details.component';

describe('UnacceptableArticleDetailsComponent', () => {
  let component: UnacceptableArticleDetailsComponent;
  let fixture: ComponentFixture<UnacceptableArticleDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnacceptableArticleDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnacceptableArticleDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
