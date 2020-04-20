import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConstituencyVicePresidentPanelComponent } from './constituency-vice-president-panel.component';

describe('ConstituencyVicePresidentPanelComponent', () => {
  let component: ConstituencyVicePresidentPanelComponent;
  let fixture: ComponentFixture<ConstituencyVicePresidentPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConstituencyVicePresidentPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConstituencyVicePresidentPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
