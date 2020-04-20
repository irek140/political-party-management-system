import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleRemoveComponent } from './article-remove.component';

describe('ArticleRemoveComponent', () => {
  let component: ArticleRemoveComponent;
  let fixture: ComponentFixture<ArticleRemoveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleRemoveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
