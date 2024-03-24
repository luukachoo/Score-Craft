package com.example.login

import com.core.common.resource.Resource
import com.core.domain.use_case.auth.GetAuthUseCase
import com.core.domain.use_case.validation.ValidationUseCase
import com.example.login.event.LoginEvent
import com.example.login.screen.LoginFragmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginFragmentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    private lateinit var viewModel: LoginFragmentViewModel
    private lateinit var getAuthUseCase: GetAuthUseCase
    private lateinit var validationUseCase: ValidationUseCase

    @Before
    fun setUp() {
        getAuthUseCase = mock(GetAuthUseCase::class.java)
        validationUseCase = mock(ValidationUseCase::class.java)
        viewModel = LoginFragmentViewModel(getAuthUseCase, validationUseCase)
    }

    @Test
    fun `login event triggers validation and login process`() = runTest {
        // Given
        val email = "test@example.com"
        val password = "password123"
        val authResource = Resource.Success(null)  // Assuming null for simplicity, use proper type

        `when`(validationUseCase.fieldValidatorUseCase(anyList())).thenReturn(true)
        `when`(getAuthUseCase.getLoginUseCase(email, password)).thenReturn(flow { emit(authResource) })

        // When
        viewModel.onEvent(LoginEvent.LogIn(email, password))

        // Then
        verify(validationUseCase).fieldValidatorUseCase(anyList())
        verify(getAuthUseCase).getLoginUseCase(email, password)
    }

    @Test
    fun `navigateToForgotPasswordPage triggers navigation event`() = runTest {
        // When
        viewModel.navigateToForgotPasswordPage()

        // Then
        viewModel.uiEvent.collect { event ->
            assertThat(event).isEqualTo(LoginFragmentViewModel.LogInUiEvent.NavigateToForgotPasswordPage)
        }
    }

    @Test
    fun `navigateToRegister triggers navigation event`() = runTest {
        // When
        viewModel.navigateToRegister()

        // Then
        viewModel.uiEvent.collect { event ->
            assertThat(event).isEqualTo(LoginFragmentViewModel.LogInUiEvent.NavigateToRegister)
        }
    }
}
